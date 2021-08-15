package com.bytelogics.webookserver.controllers

import com.bytelogics.webookserver.exceptions.*
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import java.util.*


@ControllerAdvice
class ControllerExceptionHandler {

    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(value = [(EntityAlreadyExistsException::class)])
    fun handleUserAlreadyExists(ex: EntityAlreadyExistsException, request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(Date(),
            "Validation failed",
            ex.message!!
        )
        return ResponseEntity(errorDetails, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(value = [(EntityNotFoundException::class)])
    fun handleEntityNotFound(ex: EntityNotFoundException, request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(Date(),
            "Unable to find resource",
            ex.message!!
        )
        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [(CannotInsertBookingException::class)])
    fun handleEntityMismatchException(ex: CannotInsertBookingException, request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(Date(),
            "Booking cannot be made due to unavailability",
            ex.message!!
        )
        return ResponseEntity(errorDetails, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(value = [(EntityMismatchException::class)])
    fun handleEntityMismatchException(ex: EntityMismatchException, request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(Date(),
            "Entity mismatch error",
            ex.message!!
        )
        return ResponseEntity(errorDetails, HttpStatus.CONFLICT)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(e: HttpMessageNotReadableException?): ResponseEntity<ErrorDetails> {
        logger.error("Returning HTTP 400 Bad Request", e)
        val errorDetails = ErrorDetails(Date(),
            "Entity mismatch error",
            e?.message!!
        )
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }
}