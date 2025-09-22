# Árvores Multiway — TDE Trie

Disciplina: Resolução de Problemas Estruturados em Computação — PUCPR  
Entrega: vídeo até 10 min no YouTube (link)

## 1) Pesquisa rápida (conceitos e usos)
- **Árvore B / B+ / B\***: árvores multiway balanceadas; usadas em **bancos de dados** para indexação em disco. B+: dados nas folhas e folhas encadeadas; B\* melhora ocupação dos nós.  
- **2–3 / 2–3–4**: versões didáticas de B-Tree (nós com 2 ou 3 chaves; até 4 filhos). Base conceitual de árvores rubro-negras.  
- **Trie** (escolhida): árvore de prefixos para **strings** (autocompletar/dicionários). Inserção, busca e remoção por caracteres.  
- **Patrícia**: variação da trie com compressão de caminhos (nós intermediários colapsados).  
- **R / R+**: árvores para **dados espaciais** (geoprocessamento/GIS). R+ evita sobreposições entre nós.  
- **QuadTree**: divisão recursiva do espaço em 4 quadrantes (imagens, jogos, colisão, queries espaciais).

## 2) Estrutura escolhida: Trie (a..z)
- Implementação própria, **sem estruturas prontas** (sem List/ArrayList/Vector/StringBuilder etc.).  
- **Sem try/catch**.  
- **Sem `.length` de array** (usamos constantes fixas); `.length` somente em `String`.  
- Operações: `insert`, `search`, `remove`, `startsWith`, `printAll`.

## 3) Como compilar e executar (VS Code/PowerShell/cmd)
```bash
javac TrieDemo.java
java TrieDemo
