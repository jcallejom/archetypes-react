package com.archetype.architectural.dto.domainx;

import org.springframework.validation.annotation.Validated;

import com.archetype.architectural.dto.domainx.enums.CanalVenta;
import com.archetype.architectural.dto.validation.ValidateNif;
import com.archetype.base.core.model.request.BaseRequest;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Validated
@ValidateNif
public final class FindClientStatusQuery extends BaseRequest{
	@NotNull(message="{message_nif_not_null}")
    private final String numeroDocumento;
    @NotNull(message="{messaje_canal}")
    private final CanalVenta canalVta;
    private final Boolean condicionesServicio;

}