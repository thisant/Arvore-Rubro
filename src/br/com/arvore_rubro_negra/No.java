package br.com.arvore_rubro_negra;

class No {

    public long dados;
    public No pai;
    public No esquerdo;
    public No direito;
    int cor;

    public long getDados() {
        return dados;
    }

    public void setDados(long dados) {
        this.dados = dados;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public No getEsquerdo() {
        return esquerdo;
    }

    public void setEsquerdo(No esquerdo) {
        this.esquerdo = esquerdo;
    }

    public No getDireito() {
        return direito;
    }

    public void setDireito(No direito) {
        this.direito = direito;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }
    
}
