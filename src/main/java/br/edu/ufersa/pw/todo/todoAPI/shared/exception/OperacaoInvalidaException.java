package br.edu.ufersa.pw.todo.todoAPI.shared.exception;

public class OperacaoInvalidaException extends NegocioException {
    private static final long serialVersionUID = 1L;
    public OperacaoInvalidaException(String mensagem) {
        super(mensagem); }
}
