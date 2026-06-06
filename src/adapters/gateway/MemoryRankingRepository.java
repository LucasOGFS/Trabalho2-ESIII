package adapters.gateway;

import entities.Ranking;
import entities.RegistroRanking;
import useCase.ports.RepositoryRanking;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    public class MemoryRankingRepository implements RepositoryRanking {
        private final String caminhoArquivo = "ranking.txt";
        private static final int LIMITE_RANKING = 10;
        private static final String SEPARADOR_JOGO = "----------------------";
        @Override
        public List<RegistroRanking> obterRankings(String nomeJogo) {
            Map<String, List<RegistroRanking>> todosRankings = lerTodosOsRankings();

            List<RegistroRanking> rankingJogo = todosRankings.getOrDefault(nomeJogo, new ArrayList<>());

            // 3. Garante que os registros retornem ordenados decrescentemente (do maior para o menor)
            rankingJogo.sort((r1, r2) -> Integer.compare(r2.getPontuacao(), r1.getPontuacao()));

            return rankingJogo;
        }
        @Override
        public void salvarRegistroNoRanking(String nomeJogo, RegistroRanking registro) {
            // 1. Carrega todos os rankings atuais em memória
            Map<String, List<RegistroRanking>> todosRankings = lerTodosOsRankings();
            // 2. Adiciona o novo registro na lista do jogo correspondente
            todosRankings.computeIfAbsent(nomeJogo, k -> new ArrayList<>()).add(registro);
            // 3. Salva e reescreve todo o arquivo atualizando as tabelas de 10 colocados
            salvarTodosOsRankings(todosRankings);
        }
        // Método auxiliar para ler o arquivo estruturado e carregar na memória
        private Map<String, List<RegistroRanking>> lerTodosOsRankings() {
            Map<String, List<RegistroRanking>> todosRankings = new HashMap<>();
            File arquivo = new File(caminhoArquivo);
            if (!arquivo.exists()) {
                return todosRankings;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                String jogoAtual = null;
                while ((linha = reader.readLine()) != null) {
                    linha = linha.trim();
                    if (linha.isEmpty()) {
                        continue;
                    }
                    // Se encontrar a linha divisora, reinicia o contexto do jogo atual
                    if (linha.equals(SEPARADOR_JOGO)) {
                        jogoAtual = null;
                        continue;
                    }
                    // Se a linha contiver ';', ela é um registro (Iniciais;Pontos)
                    if (linha.contains(";")) {
                        if (jogoAtual != null) {
                            String[] partes = linha.split(";");
                            if (partes.length == 2) {
                                String iniciais = partes[0];
                                int pontuacao = Integer.parseInt(partes[1]);
                                todosRankings.computeIfAbsent(jogoAtual, k -> new ArrayList<>())
                                        .add(new RegistroRanking(pontuacao, iniciais));
                            }
                        }
                    } else {
                        // Se não tiver ';' e não for o divisor, a linha é o nome do Jogo
                        jogoAtual = linha;
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.err.println("Erro ao ler o arquivo de ranking: " + e.getMessage());
            }
            return todosRankings;
        }
        // Método auxiliar para ordenar, filtrar os 10 melhores e salvar tudo no mesmo arquivo
        private void salvarTodosOsRankings(Map<String, List<RegistroRanking>> todosRankings) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, false))) { // false = sobrescreve

                for (Map.Entry<String, List<RegistroRanking>> entrada : todosRankings.entrySet()) {
                    String nomeJogo = entrada.getKey();
                    List<RegistroRanking> registros = entrada.getValue();
                    // Ordena decrescente pela pontuação
                    registros.sort((r1, r2) -> Integer.compare(r2.getPontuacao(), r1.getPontuacao()));
                    // Limita a lista ao máximo de 10 registros
                    int limite = Math.min(registros.size(), LIMITE_RANKING);
                    // Escreve o nome do Jogo
                    writer.write(nomeJogo);
                    writer.newLine();
                    // Escreve os registros qualificados (até o limite de 10)
                    for (int i = 0; i < limite; i++) {
                        RegistroRanking reg = registros.get(i);
                        writer.write(reg.getIniciais() + ";" + reg.getPontuacao());
                        writer.newLine();
                    }
                    // Escreve o divisor gráfico
                    writer.write(SEPARADOR_JOGO);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Erro ao salvar os rankings no arquivo: " + e.getMessage());
            }
        }
    }
