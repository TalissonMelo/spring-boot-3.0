package com.talissonmelo.controlador.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.talissonmelo.modelo.exception.ConflitoEmDelecao;
import com.talissonmelo.modelo.exception.EntidadeNaoEncontrada;
import com.talissonmelo.modelo.exception.Erros;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHanlder extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(ApiExceptionHanlder.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		log.error(e.getMessage(), e);
		String detalhe = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
		List<String> mensagens = new ArrayList<String>();
		for (ObjectError error : e.getBindingResult().getAllErrors()) {
			String msg = error.getDefaultMessage();
			mensagens.add(msg);
		}
		Erros erros = new Erros();
		erros.setStatus(status.value());
		erros.setTimestamp(OffsetDateTime.now());
		erros.setDescricao(detalhe);
		erros.setMensagens(mensagens);
		return handleExceptionInternal(e, erros, headers, status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontrada.class)
	public ResponseEntity<Erros> entityNotFound(EntidadeNaoEncontrada e, HttpServletRequest request) {
		log.error(e.getMessage(), e);
		Erros erros = new Erros();
		erros.setStatus(HttpStatus.NOT_FOUND.value());
		erros.setDescricao(e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erros);

	}

	@ExceptionHandler(ConflitoEmDelecao.class)
	public ResponseEntity<Erros> dataViolation(ConflitoEmDelecao e, HttpServletRequest request) {
		log.error(e.getMessage(), e);
		Erros erros = new Erros();
		erros.setStatus(HttpStatus.CONFLICT.value());
		erros.setDescricao(e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erros);
	}
}
