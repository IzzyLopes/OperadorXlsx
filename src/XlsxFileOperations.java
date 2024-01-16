import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * Classe de operações num arquivo xlsx 
 */
public class XlsxFileOperations {

    private List<Servidor> listaServidores = new ArrayList<>();

    /* Método de leitura do arquivo */
    public void lerArqXlsxServidores(BufferedInputStream buf) {
        System.out.println("Lendo arquivo no formato XLS dos Servidores");    
        try {
            XSSFWorkbook wb = new XSSFWorkbook(buf);
            Sheet sheet = wb.getSheetAt(0);
            Iterator linhas = sheet.rowIterator();
            System.out.println("Aberto arquivo XLS dos servidores.\nSerá iniciado a leitura de registro por registro");

            while (linhas.hasNext()) {
                XSSFRow linha = (XSSFRow) linhas.next();
                Iterator celulas = linha.cellIterator();

                Servidor servidor = new Servidor();
                
                while (celulas.hasNext()) {
                    XSSFCell celula = (XSSFCell) celulas.next();
                    int z = celula.getColumnIndex();

                    switch (z) {
                        case 5: // Coluna F
                            servidor.setCodSexo(celula.toString());
                        case 7: // Coluna H
                            /*
                             * Não foi possível converter o valor no excel para o tipo data pois o valor fornecido gera erro no código,
                             * tendo em vista que não a uma separação de dia, mes e ano que não gere uma excessão.
                             * Exemplo (valor da linha 1 na Coluna H) = 35587
                             * Dia - 35 (esse dia não existe)
                             * Mês - 5
                             * Ano - 87
                             */
                            servidor.setDataNascimento(celula.toString());
                        case 9: // Coluna J
                            servidor.setNomeServidor(celula.toString());
                        default:
                            continue;
                    }
                }

                listaServidores.add(servidor);
            }
            System.out.println("Leitura do arquivo do arquivo de Servidores no formato XLS concluído com sucesso.");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a leitura do arquivo.");
        }
    }

    /* Método de criação de um arquivo */
    public void criarExcel(String caminho) {
        FileOutputStream file = null;
        System.out.println("Montando o arquivo de excel novo.");
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Aposentaveis");
            file = new FileOutputStream(new File(caminho));
            int i = 0;
            for (Servidor serv : getListaServidores()) {
                XSSFRow row = sheet.createRow(i);

                row.createCell(0).setCellValue(serv.getNomeServidor());
                row.createCell(1).setCellValue(serv.getCodSexo());
                row.createCell(2).setCellValue(serv.getDataNascimento());
                row.createCell(3).setCellValue(serv.getAposentavel());
                i++;
            }
            workbook.write(file);
            System.out.println("Arquivo gerado.\nCaminho: " + caminho);            
        } catch (Exception e) {
            System.out.println("Erro ao gravar o arquivo novo.\nErro: "+ e.getMessage());            
        } finally {
            try {
                file.close();
                file.flush();
            } catch (IOException e) {
                System.out.println("Erro ao entrar no finally: " + e.getMessage());
            }
        }
    }

    public List<Servidor> getListaServidores() {
        return listaServidores;
    }

    public void setListaServidores(List<Servidor> listaServidores) {
        this.listaServidores = listaServidores;
    }

}
