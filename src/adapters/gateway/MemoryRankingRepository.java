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
        private final String caminhoArquivo = "./resources/repository/ranking.txt";
        private static final int LIMITE_RANKING = 10;
        private static final String SEPARADOR_JOGO = "----------------------";

        @Override
        public Ranking obterRankings(String nomeJogo) {
            List<RegistroRanking> listaRanking = new ArrayList<>();
            File arquivo = new File(caminhoArquivo);

            if (!arquivo.exists()) {
                return new Ranking(listaRanking); //se o arquivo não existir, cria uma lista vazia
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                boolean dentroDoJogo = false;

                while ((linha = reader.readLine()) != null) {
                    linha = linha.trim();

                    if (linha.isEmpty())
                        continue;

                    if (linha.equalsIgnoreCase(nomeJogo)) {
                        dentroDoJogo = true;
                        continue;
                    }

                    if (dentroDoJogo) {
                        if (linha.equals(SEPARADOR_JOGO)) {
                            return new Ranking(listaRanking);
                        }
                        // 3. Lê e adiciona o registro
                        String[] partes = linha.split(";");
                        if (partes.length == 2) {
                            String iniciais = partes[0];
                            int pontuacao = Integer.parseInt(partes[1]);
                            listaRanking.add(new RegistroRanking(pontuacao, iniciais));
                        }
                    }
                }

            } catch (IOException | NumberFormatException e) {
                System.err.println("Erro ao ler o ranking: " + e.getMessage());
            }
            // Se rodou o arquivo e não encontrou nada
            return new Ranking(listaRanking);
        }

        @Override
        public void salvarRegistroNoRanking(String nomeJogo, RegistroRanking registro) {
            Ranking rankingAtual = obterRankings(nomeJogo);
            List<RegistroRanking> registros = new ArrayList<>(rankingAtual.getRanking());
            if (rankingAtual.isElegivel(registro.getPontuacao())) {
                if (registros.size() >= 10) {
                    registros.remove(registros.size() - 1);
                }
                registros.add(registro);

            } else {
                return;
            }

            // Ordenação
            Ranking novoRanking = new Ranking(registros);
            List<RegistroRanking> registrosOrdenados = novoRanking.getRanking();

            List<String> todasAsLinhas = new ArrayList<>();
            File arquivo = new File(caminhoArquivo);
            if (arquivo.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                    String linha;
                    while ((linha = reader.readLine()) != null) {
                        todasAsLinhas.add(linha.trim());
                    }
                } catch (IOException e) {
                    System.err.println("Erro ao ler as linhas do arquivo: " + e.getMessage());
                }
            }

            List<String> novaSecaoJogo = new ArrayList<>();
            novaSecaoJogo.add(nomeJogo);
            for (RegistroRanking reg : registrosOrdenados) {
                novaSecaoJogo.add(reg.getIniciais() + ";" + reg.getPontuacao());
            }
            novaSecaoJogo.add(SEPARADOR_JOGO);

            //Define onde colocar no arquivo:
            int indiceJogo = -1;
            for (int i = 0; i < todasAsLinhas.size(); i++) {
                if (todasAsLinhas.get(i).equalsIgnoreCase(nomeJogo)) {
                    indiceJogo = i;
                    break;
                }
            }
            if (indiceJogo == -1) {
                // Se o jogo é novo, adiciona o bloco inteiro no final
                todasAsLinhas.addAll(novaSecaoJogo);
            } else {
                // Se já existe, localiza o fim da seção antiga (o divisor)
                int indiceFim = indiceJogo;
                while (indiceFim < todasAsLinhas.size() && !todasAsLinhas.get(indiceFim).equals(SEPARADOR_JOGO)) {
                    indiceFim++;
                }
                // Remove o bloco antigo (do nome do jogo até o divisor)
                int totalRemover = indiceFim - indiceJogo + 1;
                for (int i = 0; i < totalRemover; i++) {
                    todasAsLinhas.remove(indiceJogo);
                }
                todasAsLinhas.addAll(indiceJogo, novaSecaoJogo);
            }
            //Salva a lista ordenada no arquivo TXT sobrescrevendo-o
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, false))) {
                for (String linha : todasAsLinhas) {
                    writer.write(linha);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Erro ao gravar no arquivo de ranking: " + e.getMessage());
            }
        }
    }