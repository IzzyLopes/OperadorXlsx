import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Main {
    /**
     * @param args
     * @throws Exception
     */

     public static void main(String[] args) throws Exception {
        String caminho = "C:/Users/ropau/Downloads";
        File arquivo = new File(caminho + "/Estudo_de_Caso_PGFN_Isabela_Lopes.xlsx");
        BufferedInputStream buf = new BufferedInputStream(new FileInputStream(arquivo));

        XlsxFileOperations operador = new XlsxFileOperations();
        operador.lerArqXlsxServidores(buf);
        operador.criarExcel(caminho + "/Aposentaveis.xlsx");
    }
}
