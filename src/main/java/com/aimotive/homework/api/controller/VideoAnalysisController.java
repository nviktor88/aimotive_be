package com.aimotive.homework.api.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aimotive.homework.api.model.VideoAnalysis;
import com.aimotive.homework.api.repository.VideoAnalysisRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class VideoAnalysisController {

	private static final ClassLoader loader = VideoAnalysisController.class.getClassLoader();

	@Autowired
	private VideoAnalysisRepository videoAnalysisRepository;

	// get list of videos
	@GetMapping(path = "/videos")
	public List<String> getVideoList() {
		try (Stream<Path> walk = Files.walk(
				Paths.get("/home/dev/development/aimotive/backend/homework.api/src/main/resources/static/videos"))) {
			return walk.filter(Files::isRegularFile).map(path -> path.getFileName().toString()).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// serve video
	@GetMapping(path = "/video/{filename}", produces = "video/mp4")
	public ResponseEntity<Resource> getVideo(@PathVariable String filename) throws IOException {
		File videoFile = new File(loader.getResource("static/videos/" + filename).getFile());
		Path path = Paths.get(videoFile.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		return ResponseEntity.ok().contentLength(videoFile.length()).body(resource);
	}

	// create video analysis record
	@PostMapping("/videoanalysis")
	public VideoAnalysis createVideoAnalysis(@RequestBody VideoAnalysis videoanalysis) {
		return videoAnalysisRepository.save(videoanalysis);
	}

	// get video analysis by file name
	@GetMapping("/videoanalysis/{fileName}")
	public ResponseEntity<VideoAnalysis> getVideoAnalysisByFileName(@PathVariable String fileName) {
		VideoAnalysis videoAnalysis = videoAnalysisRepository.findById(fileName).orElse(new VideoAnalysis(fileName, null));
		return ResponseEntity.ok(videoAnalysis);
	}
}
