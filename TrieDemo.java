import java.util.Scanner;

// trie de a..z (minúsculas) sem usar estruturas prontas e sem usar .length de array (só de String)
class TrieNode {
    // número fixo de filhos (26), não uso children.length em lugar nenhum
    private static final int ALPH = 26;
    TrieNode[] next = new TrieNode[ALPH];
    boolean isWord;
}

class Trie {
    private static final int ALPH = 26; // precisa ser igual ao do nó
    private TrieNode root = new TrieNode();

    // mapeia 'a'..'z' para 0..25; retorna -1 se caractere inválido
    private int idx(char c) {
        if (c >= 'a' && c <= 'z') return c - 'a';
        if (c >= 'A' && c <= 'Z') return c - 'A'; // permite maiúsculas na entrada
        return -1;
    }

    // insere uma palavra (string) na trie
    public void insert(String s) {
        TrieNode cur = root;
        // permitido: String.length()
        for (int i = 0; i < s.length(); i = i + 1) {
            int j = idx(s.charAt(i));
            if (j < 0 || j >= ALPH) {
                // ignora caracteres fora de a..z; alternativa: poderia abortar
                continue;
            }
            if (cur.next[j] == null) {
                cur.next[j] = new TrieNode();
            }
            cur = cur.next[j];
        }
        cur.isWord = true;
    }

    // busca palavra completa
    public boolean search(String s) {
        TrieNode cur = root;
        for (int i = 0; i < s.length(); i = i + 1) {
            int j = idx(s.charAt(i));
            if (j < 0 || j >= ALPH) return false;
            if (cur.next[j] == null) return false;
            cur = cur.next[j];
        }
        return cur.isWord;
    }

    // verifica se existe alguma palavra com esse prefixo
    public boolean startsWith(String s) {
        TrieNode cur = root;
        for (int i = 0; i < s.length(); i = i + 1) {
            int j = idx(s.charAt(i));
            if (j < 0 || j >= ALPH) return false;
            if (cur.next[j] == null) return false;
            cur = cur.next[j];
        }
        return true;
    }

    // remoção de palavra: retorna true se o nó atual pode ser apagado pelo pai
    public boolean remove(String s) {
        return removeRec(root, s, 0);
    }

    private boolean removeRec(TrieNode node, String s, int pos) {
        if (node == null) return false;

        if (pos == s.length()) {
            if (!node.isWord) return false; // palavra não existia
            node.isWord = false;
            // se não tem filhos, indica que pode ser removido
            return noChildren(node);
        }

        int j = idx(s.charAt(pos));
        if (j < 0 || j >= ALPH) return false; // caractere inválido
        TrieNode child = node.next[j];
        if (child == null) return false; // palavra não existe

        boolean apagarFilho = removeRec(child, s, pos + 1);

        // se o filho pode ser removido e não é necessário para outras palavras
        if (apagarFilho) {
            node.next[j] = null;
        }

        // este nó pode ser removido se:
        // - não marca fim de palavra
        // - e ficou sem filhos
        return !node.isWord && noChildren(node);
    }

    private boolean noChildren(TrieNode node) {
        // checa todos os 26 ponteiros sem usar .length
        int i = 0;
        while (i < 26) {
            if (node.next[i] != null) return false;
            i = i + 1;
        }
        return true;
    }

    // imprime todas as palavras em ordem lexicográfica
    public void printAll() {
        printDfs(root, "");
    }

    private void printDfs(TrieNode node, String prefix) {
        if (node == null) return;
        if (node.isWord) {
            System.out.println(prefix);
        }
        // itera 0..25 (sem usar .length)
        int i = 0;
        while (i < 26) {
            if (node.next[i] != null) {
                char c = (char) ('a' + i);
                // concatenação de String é permitida (não uso StringBuilder diretamente)
                printDfs(node.next[i], prefix + c);
            }
            i = i + 1;
        }
    }
}

public class TrieDemo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Trie trie = new Trie();

        // exemplos iniciais (aplicação prática: autocompletar / dicionário)
        trie.insert("carro");
        trie.insert("casa");
        trie.insert("casaco");
        trie.insert("casar");
        trie.insert("dog");

        while (true) {
            System.out.println();
            System.out.println("menu trie:");
            System.out.println("1 inserir palavra");
            System.out.println("2 buscar palavra");
            System.out.println("3 remover palavra");
            System.out.println("4 comeca com (prefixo)");
            System.out.println("5 imprimir todas");
            System.out.println("0 sair");
            System.out.print("opcao: ");

            int op = lerOpcao(in);
            if (op == 0) {
                System.out.println("encerrando.");
                break;
            } else if (op == 1) {
                System.out.print("palavra: ");
                String s = in.next();
                trie.insert(s);
                System.out.println("inserido: " + s);
            } else if (op == 2) {
                System.out.print("palavra: ");
                String s = in.next();
                boolean ok = trie.search(s);
                System.out.println(ok ? "encontrada" : "nao encontrada");
            } else if (op == 3) {
                System.out.print("palavra: ");
                String s = in.next();
                boolean removeu = trie.remove(s);
                // a função retorna se o nó raiz ficaria vazio; para o usuário, confirmo se a palavra existia
                System.out.println(trie.search(s) ? "nao removida" : "removida (se existia)");
            } else if (op == 4) {
                System.out.print("prefixo: ");
                String p = in.next();
                boolean ok = trie.startsWith(p);
                System.out.println(ok ? "existe alguma palavra com esse prefixo" : "nenhuma palavra com esse prefixo");
            } else if (op == 5) {
                System.out.println("todas as palavras:");
                trie.printAll();
            } else {
                System.out.println("opcao invalida.");
            }
        }
        in.close();
    }

    private static int lerOpcao(Scanner in) {
        if (!in.hasNextInt()) {
            in.next();
            return -1;
        }
        return in.nextInt();
    }
}
