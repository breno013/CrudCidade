# Crud: Cidades, Estados e Países. #BeTheNext Ada/Sinqia

---

## Requisitos: 
1. JVM correspondente à versão do Java 18.
2. O arquivo ```crud.properties``` precisa estar na raiz do projeto.

---

## Configuração:
É necessário definir o tipo de br.com.ada.crud.controller e persistência.
O arquivo responsável por isso é nomeado de ```crud.properties```.

### 1. Valores de Controller:

##### Definitivo:
   
   * **O que é:** 
   O conteúdo coletado pelo CRUD será salvo em arquivo.

   &NewLine;

   * **Como aplicar valor:**
   ```pessoa.br.com.ada.crud.controller.tipo=DEFINITIVO```

##### Volátil:
   * **O que é:** 
   O conteúdo coletado pelo CRUD não será salvo em arquivo. 
   Por isso, ao fechar o programa, as informações serão perdidas.

   &NewLine;

   * **Como aplicar o valor:**
   ```pessoa.br.com.ada.crud.controller.tipo=VOLATIL```

### 2. Valores de persistência:

##### XML:
   * **O que é:**
   Os dados serão alocados no arquivo XML - uma linguagem de marcação.   

   &NewLine;

   * **Como aplicar valor:**
   ```pessoa.persistência.tipo=XML```

##### BINARIA:
   * **O que é:**
   Os dados serão alocados em binário.
   
   &NewLine;   

   * **Como aplicar o valor:**
   ```pessoa.persistência.tipo=BINARIA```