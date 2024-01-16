
public class Servidor {
    private String nomeServidor;
    private String codSexo;
    private String dataNascimento;
    private Boolean Aposentavel;

    public Servidor() {
        this.nomeServidor = null;
        this.codSexo = null;
        this.dataNascimento = null;
        this.Aposentavel = false;
    }

    public String getNomeServidor() {
        return nomeServidor;
    }

    public void setNomeServidor(String nomeServidor) {
        this.nomeServidor = nomeServidor;
    }

    public String getCodSexo() {
        return codSexo;
    }

    public void setCodSexo(String codSexo) {
        this.codSexo = codSexo;
    }
    
    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getAposentavel() {
        return Aposentavel;
    }

    public void setAposentavel(Boolean aposentavel) {
        Aposentavel = aposentavel;
    }
}
