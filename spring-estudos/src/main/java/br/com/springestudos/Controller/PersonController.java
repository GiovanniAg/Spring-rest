package br.com.springestudos.Controller;

import java.util.List;

import br.com.springestudos.Service.PersonService;
import br.com.springestudos.Util.MediaType;
import br.com.springestudos.data.vo.v1.PersonVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/person")
@Tag(name ="People", description = "Endpoints for managing people")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(
                produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all People", description = "Finds all People",
                tags = {"People"},
                responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = {
                        @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation =  PersonVO.class))
                        )
                }),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
)
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}",
                produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds one person", description = "Finds one person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                            @Content(
                                    schema = @Schema(implementation =  PersonVO.class))
                                ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                 produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Adds one person", description = "Adds one person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(
                            schema = @Schema(implementation =  PersonVO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public PersonVO create(@RequestBody PersonVO person) {
        return service.create(person);
    }

    @PutMapping(
                consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Change one person", description = "Change one person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(
                            schema = @Schema(implementation =  PersonVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public PersonVO update(@RequestBody PersonVO person) {
        return service.update(person);
    }


    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes one person", description = "Delets one person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}