package gr.sgdigital.common.base;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

public class BaseTestController {
	public String generateAcceptedJson (Object data) throws JsonProcessingException {
		return generateJson (new AcceptedStatus(), data);
	}
	public String generateBadRequestJson (Object data, String desc) throws JsonProcessingException {
		return generateJson (new BadRequestException(desc), data);
	}
	public String generateConflictJson (Object data, String desc) throws JsonProcessingException {
		return generateJson (new ConflictException(desc), data);
	}
	public String generateCreatedJson (Object data) throws JsonProcessingException {
		return generateJson (new CreatedStatus(), data);
	}
	public String generateForbiddenJson (Object data, String desc) throws JsonProcessingException {
		return generateJson (new ForbiddenException(desc), data);
	}
	public String generateMethodNotAllowedJson (Object data, String desc) throws JsonProcessingException {
		return generateJson (new MethodNotAllowedException(desc), data);
	}
	public String generateNoContentJson (Object data) throws JsonProcessingException {
		return generateJson (new NoContentStatus(), data);
	}
	public String generateNotFoundJson (Object data, String desc) throws JsonProcessingException {
		return generateJson (new NotFoundException(desc), data);
	}
	public String generateRequestTimeoutJson (Object data, String desc) throws JsonProcessingException {
		return generateJson (new RequestTimeoutException(desc), data);
	}
	public String generateSuccessJson (Object data) throws JsonProcessingException {
		return generateJson (new SuccessStatus(), data);
	}
	public String generateUnauthorizedJson (Object data, String desc) throws JsonProcessingException {
		return generateJson (new UnauthorizedException(desc), data);
	}

	/**
	 * Initialise a custom json mapper to skip the serialization of date time elements
	 * If not timestamps will not match causing tests to fail
	 */
	public String generateJson (ApiStatus status, Object data) throws JsonProcessingException {
		var response = new ApiResponse<Object>(status, data);
		var objectMapper = new ObjectMapper ();

		objectMapper.addMixIn(LocalDateTime.class, MyMixInForIgnoreType.class);

		return objectMapper.writeValueAsString(response);
	}

	@JsonIgnoreType
	public class MyMixInForIgnoreType {}
}



