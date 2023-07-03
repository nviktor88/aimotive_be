package com.aimotive.homework.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "video_analysis")
public class VideoAnalysis {
	@Id
	@Column(name = "file_name")
	private String fileName;
	
	@Lob
	@Column(name = "marked_frames")
	private String markedFrames;
	
	public VideoAnalysis() {}
	
	public VideoAnalysis(String fileName, String markedFrames) {
		super();
		this.fileName = fileName;
		this.markedFrames = markedFrames;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMarkedFrames() {
		return markedFrames;
	}

	public void setMarkedFrames(String markedFrames) {
		this.markedFrames = markedFrames;
	}
}
