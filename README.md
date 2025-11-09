# Sistema de Controle de Recursos (SCR)  
Aplicação em Java voltada à **simulação de gerenciamento de recursos**, utilizando **estruturas dinâmicas** e **abordagem modular** para controle, registro e priorização de operações. Projeto projetado para disciplinas práticas de Estruturas de Dados e Programação Orientada a Objetos, com ênfase em autoria completa, eficiência e clareza de código.  

<p align="center">
  <img src="https://img.shields.io/badge/Java-17%2B-blue" />
  <img src="https://img.shields.io/badge/Build-javac%2Fjar-informational" />
  <img src="https://img.shields.io/badge/License-MIT-success" />
</p>

---

## Objetivos do projeto  
* Controlar a alocação, liberação e monitoramento de recursos em uma estrutura de dados autoral.  
* Aplicar conceitos de **listas encadeadas, filas e pilhas** para simular filas de espera, execução e histórico de operações.  
* Reforçar o domínio sobre ponteiros, complexidade e boas práticas em Java puro, sem o uso de coleções prontas (`ArrayList`, `LinkedList`, etc).  
* Permitir o registro e visualização detalhada do estado atual dos recursos e operações pendentes.  

---

## Principais funcionalidades  
* **Registrar recursos** com identificadores únicos.  
* **Alocar** recursos para tarefas em fila de espera.  
* **Liberar** recursos, movendo-os para uma pilha de histórico.  
* **Listar** o estado atual de todos os recursos (disponíveis, alocados, em espera).  
* **Registrar logs** de atividades executadas e falhas.  
* **Gerar relatórios** com estatísticas básicas (uso, média de tempo, taxa de ocupação).  

---

## Estruturas de dados implementadas  

**Lista Encadeada Base (`ListaEncadeada<T>`):**  
* Implementa inserção, remoção e iteração manual.  
* Complexidade O(1) nas extremidades, O(n) nas buscas.  

**Fila de Espera (`FilaRecursos`):**  
* Baseada em ponteiros `inicio` e `fim`.  
* Métodos principais:  
  * `enfileirar(T recurso)`  
  * `desenfileirar()`  
  * `primeiro()`  
  * `vazia()`  

**Pilha de Histórico (`PilhaRecursos`):**  
* Estrutura LIFO para registrar o histórico de ações realizadas.  
* Métodos principais:  
  * `empilhar(T recurso)`  
  * `desempilhar()`  
  * `topo()`  
  * `vazia()`  

**Gerenciador de Recursos (`GerenciadorRecursos`):**  
* Camada intermediária que integra as estruturas.  
* Controla o ciclo de vida dos recursos: registro → alocação → liberação → histórico.  
* Implementa verificações de consistência e relatórios dinâmicos.  

---


## Modelo de dados  

**Recurso.java**  
* id: String  
* nome: String  
* tipo: String  
* status: enum { DISPONIVEL, ALOCADO, EM_ESPERA }  
* tempoAlocacao: LocalDateTime  

**Operacao.java**  
* id: String  
* descricao: String  
* dataHora: LocalDateTime  
* tipoOperacao: enum { REGISTRO, ALOCACAO, LIBERACAO }  

---

## Validações e consistência  
* Não permitir alocar um recurso já alocado.  
* Liberação só possível se o recurso estiver em uso.  
* IDs duplicados não são aceitos.  
* Operações inválidas retornam mensagens claras e não quebram o fluxo.  

---

## Geração de relatórios  
**Relatório de uso:**  
* Total de recursos registrados.  
* Taxa de ocupação (%) = (recursos alocados / total) × 100.  
* Tempo médio de alocação.  


---

## Testes unitários  
**ListaEncadeadaTest:** inserção, remoção e busca.  
**FilaRecursosTest:** ordem FIFO garantida.  
**PilhaRecursosTest:** ordem LIFO garantida.  
**GerenciadorRecursosTest:** fluxo completo de alocação/liberação.  


---

## Diagrama conceitual  
```mermaid
flowchart TB
  subgraph Estruturas
    L[ListaEncadeada]
    F[FilaRecursos]
    P[PilhaRecursos]
  end
  subgraph Domínio
    R[Recurso]
    O[Operacao]
  end
  subgraph Controle
    G[GerenciadorRecursos]
  end
  L --> F
  L --> P
  F --> G
  P --> G
  G --> R
  G --> O
