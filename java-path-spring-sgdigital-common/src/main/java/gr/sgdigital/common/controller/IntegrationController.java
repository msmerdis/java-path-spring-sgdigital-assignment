package gr.sgdigital.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.base.AbstractComponent;
import gr.sgdigital.common.transfer.ApiResponse;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.common.transfer.status.AcceptedStatus;
import gr.sgdigital.common.transfer.status.BadRequestException;
import gr.sgdigital.common.transfer.status.ConflictException;
import gr.sgdigital.common.transfer.status.CreatedStatus;
import gr.sgdigital.common.transfer.status.ForbiddenException;
import gr.sgdigital.common.transfer.status.MethodNotAllowedException;
import gr.sgdigital.common.transfer.status.NoContentStatus;
import gr.sgdigital.common.transfer.status.NotFoundException;
import gr.sgdigital.common.transfer.status.RequestTimeoutException;
import gr.sgdigital.common.transfer.status.SuccessStatus;
import gr.sgdigital.common.transfer.status.UnauthorizedException;

/**
 * This class represents a test controller
 */
@RestController
@RequestMapping("/api/integration")
public class IntegrationController extends AbstractComponent {

	@GetMapping()
	public ApiResponse<String> ok() {
		logger.info("Test endpoint returns OK");
		return new ApiResponse<String> (new SuccessStatus(), "OK");
	}

	@GetMapping("/accepted")
	public String accepted() throws ApiStatus {
		logger.info("Emulate an accepted status");
		throw new AcceptedStatus();
	}

	@GetMapping("/badrequest")
	public String badRequest() throws ApiStatus {
		logger.info("Emulate a bad request");
		throw new BadRequestException("Replicating a bad request error");
	}

	@GetMapping("/conflict")
	public String conflict() throws ApiStatus {
		logger.info("Emulate a conflict error");
		throw new ConflictException("Replicating a conflict error");
	}

	@GetMapping("/created")
	public String created() throws ApiStatus {
		logger.info("Emulate a created status");
		throw new CreatedStatus();
	}

	@GetMapping("/forbidden")
	public String forbidden() throws ApiStatus {
		logger.info("Emulate a forbidden request");
		throw new ForbiddenException("Replicating a forbidden error");
	}

	@GetMapping("/methodnotallowed")
	public String methodNotAllowed() throws ApiStatus {
		logger.info("Emulate an incorrect method used for request");
		throw new MethodNotAllowedException("Replicating a method not allowed error");
	}

	@GetMapping("/nocontent")
	public String noContent() throws ApiStatus {
		logger.info("Emulate a no content status");
		throw new NoContentStatus();
	}

	@GetMapping("/notfound")
	public String notFound() throws ApiStatus {
		logger.info("Emulate a not found error");
		throw new NotFoundException("Replicating a not found error");
	}

	@GetMapping("/requesttimeout")
	public String requestTimeout() throws ApiStatus {
		logger.info("Emulate a timeout");
		throw new RequestTimeoutException("Replicating a request timeout");
	}

	@GetMapping("/success")
	public String success() throws ApiStatus {
		logger.info("Emulate a success status");
		throw new SuccessStatus();
	}

	@GetMapping("/unauthorized")
	public String unauthorized() throws ApiStatus {
		logger.info("Emulate an unauthorized request");
		throw new UnauthorizedException("Replicating an unauthorized exception");
	}

	@GetMapping("/internalservererror")
	public String internalServerError() throws Exception {
		logger.info("Emulate an unauthorized request");
		throw new Exception("Replicating an internal server error exception");
	}
}



