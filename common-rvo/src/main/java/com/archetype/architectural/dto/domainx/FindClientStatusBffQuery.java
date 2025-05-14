package com.archetype.architectural.dto.domainx;

import org.springframework.validation.annotation.Validated;

import com.archetype.architectural.dto.validation.ValidateNif;
import com.archetype.base.core.model.request.BaseRequest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Validated
@ValidateNif
public final class FindClientStatusBffQuery extends BaseRequest{
	@NotNull(message="{message_nif_not_null}")
    private final String numeroDocumento;
    @NotNull(message="{messaje_canal}")
    private final String canalVta;
    private final Boolean condicionesServicio;

}