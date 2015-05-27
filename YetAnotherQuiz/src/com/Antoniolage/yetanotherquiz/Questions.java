package com.Antoniolage.yetanotherquiz;

public class Questions {
	int _id;
	String pergunta;
	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	String resposta;
	String assunto;
	public Questions(){}
	
	public Questions(int id, String Pergunta, String Assunto, String Resposta){
		this._id=id;
		this.pergunta=Pergunta;
		this.resposta=Resposta;
		this.assunto=Assunto;
	}
	public Questions(String Assunto, String Resposta){
		this.resposta=Resposta;
		this.assunto=Assunto;
	}
	 // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
    
}