package com.pratilipi.storyapp.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pratilipi.storyapp.dto.StoryDTO;
import com.pratilipi.storyapp.models.StoryCountModel;
import com.pratilipi.storyapp.models.StoryLiveCount;
import com.pratilipi.storyapp.models.StoryModel;
import com.pratilipi.storyapp.models.StoryUserMapModel;
import com.pratilipi.storyapp.models.StoryUserMapModel.StoryUserDTO;
import com.pratilipi.storyapp.repositories.StoryCountRepo;
import com.pratilipi.storyapp.repositories.StoryRepo;
import com.pratilipi.storyapp.repositories.StoryTotalCountRepo;
import com.pratilipi.storyapp.repositories.StoryUserMapRepo;

@RestController
@CrossOrigin
@RequestMapping
public class StoryController {

	Logger logger = LoggerFactory.getLogger(StoryController.class);

	@Autowired
	StoryRepo imageRepository;

	@Autowired
	StoryCountRepo storyCountRepo;

	@Autowired
	StoryUserMapRepo storyUserRepo;

	@Autowired
	StoryTotalCountRepo storyTotalCountRepo;

	@PostMapping("/image/upload")
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) {
		try {
			System.out.println("Original Image Byte Size - " + file.getBytes().length);
			StoryModel img = new StoryModel(file.getOriginalFilename(), file.getContentType(),
					compressBytes(file.getBytes()), "", "");
			imageRepository.save(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.OK);
	}

	@GetMapping(path = { "/stories" })
	public List<StoryModel> getStories() throws IOException {
		final List<StoryModel> stories = imageRepository.findAll();
		for (StoryModel img : stories) {
			img.setImageByte(decompressBytes(img.getImageByte()));
		}
		return stories;
	}

	@GetMapping(path = { "/story/{id}" })
	public synchronized int getStoryViewCount(@PathVariable("id") Long id) throws IOException {
		logger.info("Live : {} ", id);
		Optional<StoryLiveCount> count = storyCountRepo.findById(id);
		if (count.isPresent()) {
			return count.get().getCount();
		} else {
			storyCountRepo.save(new StoryLiveCount(id, 1));
			return 1;
		}
	}

	@GetMapping(path = { "/story/increment/{id}" })
	public synchronized int incrementStoryViewCount(@PathVariable("id") Long id) throws IOException {
		logger.info("Increment : {} ", id);
		Optional<StoryLiveCount> count = storyCountRepo.findById(id);
		if (count.isPresent()) {
			return storyCountRepo.save(new StoryLiveCount(id, count.get().getCount() + 1)).getCount();
		} else {
			storyCountRepo.save(new StoryLiveCount(id, 1));
			return 1;
		}
	}

	@GetMapping(path = { "/story/decrement/{id}" })
	public synchronized int decrementStoryViewCount(@PathVariable("id") Long id) throws IOException {
		logger.info("Decrement : {} ", id);
		Optional<StoryLiveCount> count = storyCountRepo.findById(id);
		if (count.isPresent()) {
			return storyCountRepo.save(new StoryLiveCount(id, count.get().getCount() - 1)).getCount();
		} else {
			storyCountRepo.save(new StoryLiveCount(id, 1));
			return 1;
		}
	}

	@PostMapping(path = { "/story/total" })
	public synchronized void incrementTotalStoryCount(@RequestBody StoryDTO dto) {

		StoryUserMapModel storyUserModel = new StoryUserMapModel();
		StoryUserDTO dto2 = new StoryUserDTO(dto.getStoryId(), dto.getUserId());

		storyUserModel.setStoryUserDTO(dto2);

		if (!storyUserRepo.findById(storyUserModel.getStoryUserDTO()).isPresent()) {

			Long id = storyUserModel.getStoryUserDTO().getStoryId();
			Optional<StoryCountModel> totalCount = storyTotalCountRepo.findById(id);

			if (totalCount.isPresent()) {
				storyTotalCountRepo.save(new StoryCountModel(id, totalCount.get().getCount() + 1));
			} else {
				storyTotalCountRepo.save(new StoryCountModel(id, 1l));
			}

			storyUserRepo.save(storyUserModel);
		}
	}

	@GetMapping(path = { "/story/total/{id}" })
	public synchronized Long getTotalStoryCount(@PathVariable("id") Long id) {
		if (storyTotalCountRepo.findById(id).isPresent()) {
			return storyTotalCountRepo.findById(id).get().getCount();
		} else {
			storyTotalCountRepo.save(new StoryCountModel(id, 1l));
			return 1l;
		}
	}

	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
}