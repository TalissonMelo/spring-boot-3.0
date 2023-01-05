package com.talissonmelo.controlador.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHanlder extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String detalhe = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente. /n ";
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String msg = error.getDefaultMessage();
			mensagens.add(new Mensagem(nome, msg));
		}
		Erros erros = new Erros();
		erros.setStatus(status.value());
		erros.setTimestamp(OffsetDateTime.now());
		erros.setMsg(detalhe);
		erros.setMensagens(mensagens);
		return handleExceptionInternal(ex, erros, headers, status, request);
	}
}
