package com.luv2code.demo.exc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;

import com.luv2code.demo.exc.custom.NotFoundException;
import com.luv2code.demo.exc.custom.QuantityNotAvailableException;

import org.springframework.validation.FieldError;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {

	private ErrorResponse buildErrorResponse(int statusCode, String exceptionName, String message, List<String> details,
			WebRequest request) {
		return new ErrorResponse(statusCode, exceptionName, message, details,
				request.getDescription(false).substring(4), System.currentTimeMillis());
	}

	private List<String> collectErrorMessages(Exception ex) {
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
		return details;
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ErrorResponse handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		return buildErrorResponse(StatusCode.FORBIDDEN, "AccessDeniedException", "No Permission.",
				collectErrorMessages(ex), request);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ErrorResponse handleDuplicateException(DataIntegrityViolationException ex, WebRequest request) {

		String message = null;

		if (ex.getCause() != null && ex.getCause().getMessage().contains("email")) {
			message = "Email Is Exist.";
		} else {
			message = "Category Is Exist.";
		}

		return buildErrorResponse(StatusCode.Conflict, "DataIntegrityViolationException", message,
				collectErrorMessages(ex), request);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ErrorResponse handleSecurityException(BadCredentialsException ex, WebRequest request) {
		return buildErrorResponse(StatusCode.UNAUTHORIZED, "BadCredentialsException", "Email or password incorrect",
				collectErrorMessages(ex), request);
	}

	@ExceptionHandler(SignatureException.class)
	public ErrorResponse handleSignatureException(SignatureException ex, WebRequest request) {
		return buildErrorResponse(StatusCode.FORBIDDEN, "SignatureException", "The JWT signature is invalid.",
				collectErrorMessages(ex), request);
	}

	@ExceptionHandler(MalformedJwtException.class)
	public ErrorResponse handleMalformedJwtException(MalformedJwtException ex, WebRequest request) {
		return buildErrorResponse(StatusCode.UNAUTHORIZED, "MalformedJwtException", "Invalid JWT token.",
				collectErrorMessages(ex), request);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ErrorResponse handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
		return buildErrorResponse(StatusCode.FORBIDDEN, "ExpiredJwtException", "The JWT token has expired.",
				collectErrorMessages(ex), request);
	}

	@ExceptionHandler(InternalServerError.class)
	public ErrorResponse handleInternalServerException(InternalServerError ex, WebRequest request) {
		return buildErrorResponse(StatusCode.INTERNAL_SERVER_ERROR, "InternalServerError",
				"Unknown Internal Server Error.", collectErrorMessages(ex), request);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
		List<String> details = ex.getBindingResult().getAllErrors().stream()
				.map(error -> ((FieldError) error).getField() + " " + error.getDefaultMessage())
				.collect(Collectors.toList());
		return buildErrorResponse(StatusCode.INVALID_ARGUMENT, "MethodArgumentNotValidException",
				"Property is invalid.", details, request);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ErrorResponse handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		List<String> details = ex.getConstraintViolations().stream().map(violation -> violation.getMessage())
				.collect(Collectors.toList());
		return buildErrorResponse(StatusCode.INVALID_ARGUMENT, "ConstraintViolationException", "Property is null.",
				details, request);
	}

	@ExceptionHandler(InsufficientAuthenticationException.class)
	public ErrorResponse handleInsufficientAuthenticationException(InsufficientAuthenticationException ex,
			WebRequest request) {
		return buildErrorResponse(StatusCode.INTERNAL_SERVER_ERROR, "InsufficientAuthenticationException",
				"Login credentials are missing.", collectErrorMessages(ex), request);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		return buildErrorResponse(StatusCode.INVALID_ARGUMENT, "IllegalArgumentException",
				"Invalid argument: " + ex.getMessage(), collectErrorMessages(ex), request);
	}

	@ExceptionHandler(NotFoundException.class)
	public ErrorResponse handleNotFoundException(NotFoundException ex, WebRequest request) {
		return buildErrorResponse(StatusCode.NOT_FOUND, "NotFoundException", ex.getMessage(), collectErrorMessages(ex),
				request);
	}

	@ExceptionHandler(QuantityNotAvailableException.class)
	public ErrorResponse handleNotFoundException(QuantityNotAvailableException ex, WebRequest request) {
		return buildErrorResponse(StatusCode.NOT_FOUND, "QuantityNotAvailableException", ex.getMessage(),
				collectErrorMessages(ex), request);
	}

	@ExceptionHandler(Exception.class)
	public ErrorResponse handleException(Exception ex, WebRequest request) {
		return buildErrorResponse(StatusCode.FORBIDDEN, "Exception", "Unknown exception.", collectErrorMessages(ex),
				request);
	}
}
