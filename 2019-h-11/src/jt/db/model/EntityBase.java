package jt.db.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@MappedSuperclass
public abstract class EntityBase {

	@Id
	@SequenceGenerator(name="ID_GENERATOR", sequenceName="AKASZTOFA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_GENERATOR")
	private Long id;

	@Version
	private Long lockversion;

	public EntityBase() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLockversion() {
		return lockversion;
	}

	public void setLockversion(Long lockversion) {
		this.lockversion = lockversion;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!this.getClass().equals(obj.getClass())){
			return false;
		}
		if(this.id != ((EntityBase)obj).id){
			return false;
		}
		return true;
	}

}
