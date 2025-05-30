# Testes Automatizados

Este documento descreve a estrutura, ferramentas e configurações utilizadas para os testes automatizados do projeto **Cuidando de Você**. O foco é garantir a qualidade do código por meio de testes unitários, de integração, cobertura de código, análise estática e visualização de dependências.

---

## Estrutura dos Testes

Os testes estão organizados na pasta:

```
cuidando/tests/
```

## Ferramentas Utilizadas

### ✅ Pytest

Framework principal de testes, com extensões:

- `pytest-django`: integração com Django
- `pytest-cov`: cobertura de código
- `pytest-mock`: uso simplificado de mocks
- `pytest-xdist`: paralelismo
- `pytest-randomly`: detecta dependências ocultas entre testes
- `pytest-sugar`: melhora a interface de execução

### ✅ Coverage

Utilizado para medir a cobertura dos testes com:

```bash
pytest --cov=cuidando --cov-report=term
```

Gera também `coverage.xml` para integração com o SonarCloud.

### ✅ Pydeps

Gera o grafo de dependências dos módulos:

```bash
docker run --rm -v "$CI_PROJECT_DIR:/app" -w /app cuidando-deps pydeps cuidando/geocoder --max-bacon=2 --show-deps --no-show -o /app/pydeps_geocoder.svg
```

## Execução Local

A execução local dos testes e geração de diagramas pode ser feita diretamente com Docker, utilizando os mesmos comandos do CI para garantir fidelidade ao ambiente de produção.

Para rodar os testes com LOCALMENTE com cobertura:

```bash
docker build -t cuidando-test   --build-arg DEPS="--extra deploy"   --build-arg ADD_PACKAGES="gdal-dev geos-dev build-base" .

docker run --rm cuidando-test pytest --cov=cuidando --cov-report=xml
```

Para gerar os diagramas de dependência LOCALMENTE com PyDeps:

```bash
docker build -t cuidando-deps   --build-arg DEPS="--extra deploy"   --build-arg ADD_PACKAGES="graphviz" .

docker run --rm -v cuidando-deps pydeps cuidando/despesas --max-bacon=2 --show-deps --no-show -o pydeps_despesas.svg

docker run --rm -v cuidando-deps pydeps cuidando/geocoder --max-bacon=2 --show-deps --no-show -o pydeps_geocoder.svg
```

Essa abordagem visa manter a consistência com o ambiente de produção **sem alterá-lo diretamente**. A criação de uma imagem separada para testes foi evitada para não introduzir discrepâncias. Em vez disso, foi utilizado um único `Dockerfile` baseado na imagem de produção, com adições controladas via argumentos (`--build-arg`) para ativar ferramentas e bibliotecas apenas durante a execução dos testes. Isso garante que os testes sejam executados em um ambiente quase idêntico ao de produção, com modificações mínimas e bem isoladas.

---

## Configuração Centralizada

A configuração das ferramentas de testes está centralizada no arquivo `pyproject.toml`, que unifica opções e dependências em um único ponto.

## Ferramentas Utilizadas

- **Python**: Linguagem de programação utilizada no desenvolvimento do sistema.
- **Pytest**: Framework principal de testes, com suporte a Django, cobertura de código, mocks, paralelismo e verificação de ordem aleatória.
- **Coverage.py**: Utilizado para medir a cobertura de testes e gerar relatórios.
- **SonarQube (SonarCloud)**: Para análise de qualidade do código, incluindo métricas de cobertura e complexidade.
- **PyDeps**: Geração de gráficos de dependência entre módulos do sistema, útil para a frente de arquitetura.
- **Docker**: Padronização do ambiente de testes com base na imagem de produção.
- **GitLab CI**: Automatiza a execução dos testes e análises como parte do pipeline CI/CD.

### Principais blocos configurados:

- **[tool.pytest.ini_options]**  
  Define:
  - Os padrões de nome de arquivos de teste
  - A localização da pasta de testes
  - Markers personalizados como `unit` e `integration`
  - O módulo de settings do Django para execução correta

- **[tool.coverage.run]**  
  Define quais arquivos e diretórios devem ser excluídos da análise de cobertura, como arquivos de migração, `settings.py` e `manage.py`, garantindo que a cobertura se concentre na lógica relevante.

- **[dependency-groups]**  
  Agrupa as dependências de desenvolvimento (incluindo Pytest, PyDeps e ferramentas auxiliares) para facilitar a instalação com `pip` ou `uv`, de forma consistente em todo o projeto.

Esse modelo reduz a complexidade do ambiente de testes e facilita sua manutenção.

---

## Considerações

- O uso de `build-base` e versões `-dev` são essenciais para compilar dependências C (ex: geos, gdal)
- A cobertura é reportada para o SonarCloud
- A documentação com `sphinx` está sendo configurada separadamente
- Algumas modificações foram feitas para rodar no GitLab CI, como:

 - No `pytest`:  
   ```bash
   -v "${CI_PROJECT_DIR}:${CI_PROJECT_DIR}" -w "${CI_PROJECT_DIR}"
   ```

 - No `pydeps`:  
   ```bash
   -v "${CI_PROJECT_DIR}:/app" -w /app ... -o /app/<arquivo>.svg
   ```

 Essas alterações garantem que o diretório correto seja acessado dentro do container durante a execução automatizada no CI.

---

## Próximos passos

- Consolidar documentação dos testes em `sphinx`
- Garantir que cobertura mínima atinja 80%

---

> *"Testar é documentar o comportamento real do código."*
