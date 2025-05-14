/**
 * Ayesa
 * @author jcallejo
 */


package com.archetype.base.core.model;

import java.time.LocalDateTime;

import org.springframework.data.relational.core.mapping.Column;

// TODO: Auto-generated Javadoc
/**
 * The Class AuditedVersionEntity.
 */

public abstract class AuditedVersionEntity extends VersionEntity {
	
	/** The fx crea reg. */

	@Column(value = "FX_CREA_REG")
	private LocalDateTime fxCreaReg;

	/** The cd usu crea reg. */
	@Column(value = "CD_USU_CREA_REG")
	private String cdUsuCreaReg;
	
	/** The ds procs crea reg. */
	@Column(value = "DS_PROCS_CREA_REG")
	private String dsProcsCreaReg;
	
	/** The fx modif reg. */
	@Column(value = "FX_MODIF_REG")
	private LocalDateTime fxModifReg;
	
	/** The cd usu modif reg. */
	@Column(value = "CD_USER_MODIF_REG")
	private String cdUsuModifReg;
	
	/** The ds procs modif reg. */
	@Column(value = "DS_PROCS_MODIF_REG")
	private String dsProcsModifReg;

	/**
	 * Gets the fx crea reg.
	 *
	 * @return the fx crea reg
	 */
	public LocalDateTime getFxCreaReg() {
		return fxCreaReg;
	}

	/**
	 * Sets the fx crea reg.
	 *
	 * @param fxCreaReg the new fx crea reg
	 */
	public void setFxCreaReg(LocalDateTime fxCreaReg) {
		this.fxCreaReg = fxCreaReg;
	}

	/**
	 * Gets the cd usu crea reg.
	 *
	 * @return the cd usu crea reg
	 */
	public String getCdUsuCreaReg() {
		return cdUsuCreaReg;
	}

	/**
	 * Sets the cd usu crea reg.
	 *
	 * @param cdUsuCreaReg the new cd usu crea reg
	 */
	public void setCdUsuCreaReg(String cdUsuCreaReg) {
		this.cdUsuCreaReg = cdUsuCreaReg;
	}

	/**
	 * Gets the ds procs crea reg.
	 *
	 * @return the ds procs crea reg
	 */
	public String getDsProcsCreaReg() {
		return dsProcsCreaReg;
	}

	/**
	 * Sets the ds procs crea reg.
	 *
	 * @param dsProcsCreaReg the new ds procs crea reg
	 */
	public void setDsProcsCreaReg(String dsProcsCreaReg) {
		this.dsProcsCreaReg = dsProcsCreaReg;
	}

	/**
	 * Gets the fx modif reg.
	 *
	 * @return the fx modif reg
	 */
	public LocalDateTime getFxModifReg() {
		return fxModifReg;
	}

	/**
	 * Sets the fx modif reg.
	 *
	 * @param fxModifReg the new fx modif reg
	 */
	public void setFxModifReg(LocalDateTime fxModifReg) {
		this.fxModifReg = fxModifReg;
	}

	/**
	 * Gets the cd usu modif reg.
	 *
	 * @return the cd usu modif reg
	 */
	public String getCdUsuModifReg() {
		return cdUsuModifReg;
	}

	/**
	 * Sets the cd usu modif reg.
	 *
	 * @param cdUsuModifReg the new cd usu modif reg
	 */
	public void setCdUsuModifReg(String cdUsuModifReg) {
		this.cdUsuModifReg = cdUsuModifReg;
	}

	/**
	 * Gets the ds procs modif reg.
	 *
	 * @return the ds procs modif reg
	 */
	public String getDsProcsModifReg() {
		return dsProcsModifReg;
	}

	/**
	 * Sets the ds procs modif reg.
	 *
	 * @param dsProcsModifReg the new ds procs modif reg
	 */
	public void setDsProcsModifReg(String dsProcsModifReg) {
		this.dsProcsModifReg = dsProcsModifReg;
	}





}
