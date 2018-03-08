package com.esense.topclass.dbmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "version_no")
    private String versionNo;
    
    @Column(name = "display_name")
    private String displayName;

    public Content(String filename, String versionNo)
	{
    	this.filename = filename;
    	this.versionNo = versionNo;
	}
    
    public Content()
	{
	}
    
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public String getVersionNo()
	{
		return versionNo;
	}

	public void setVersionNo(String versionNo)
	{
		this.versionNo = versionNo;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}
    
    
}

