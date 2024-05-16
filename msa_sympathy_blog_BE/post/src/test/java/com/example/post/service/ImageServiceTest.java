package com.example.post.service;

import com.example.post.exception.ImageNotFoundException;
import com.example.post.exception.PostNotFoundException;
import com.example.post.global.domain.entity.Image;
import com.example.post.global.domain.repository.ImageRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ImageServiceTest {
    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepository imageRepository;

    @Nested
    class save {

        @Test
        void success() {
            Long lenBefore = (long) imageRepository.findAll().size();
            imageService.save(4L, "C:\\Users\\Playdata\\Pictures\\Screenshots");
            Long lenAfter = (long) imageRepository.findAll().size();
            Image image = imageRepository.findById(lenAfter-1).get();

            assertEquals(lenBefore+1, lenAfter);
            assertNotNull(image);
            assertEquals("C:\\Users\\Playdata\\Pictures\\Screenshots", image.getPath());
        }

        @Test
        void postNotFoundFail() {
            assertThrows(PostNotFoundException.class
                    , () -> imageService
                            .save(1000L, "C:\\Users\\Playdata\\Pictures\\Screenshots"));
        }
    }

    @Test
    void delete() {
        imageService.save(4L, "C:\\Hola");
        Long lenBefore = (long) imageRepository.findAll().size();
        imageService.delete(5L);
        Long lenAfter = (long) imageRepository.findAll().size();

        assertEquals(lenBefore-1, lenAfter);
    }

    @Nested
    class update {
        @Test
        void success() {
            imageService.update(6L, "C:\\GodDamn");
            Image image = imageRepository.findById(6L).get();

            assertEquals("C:\\GodDamn", image.getPath());
        }

        @Test
        void imageNotFoundFail() {
            assertThrows(ImageNotFoundException.class, () -> imageService
                    .update(1000L,"C:\\GodDamn"));
        }
    }

    @Nested
    class findImage {
        @Test
        void success() {
            imageService.save(4L, "C:\\Wassup");
            List<Image> images = imageRepository.findAll();
            int length = images.size();
            Image image = images.get(length-1);
            Image byId = imageService.findImage(image.getId());

            assertEquals("C:\\Wassup", byId.getPath());
        }
        @Test
        void imageNotFoundFail() {
            assertThrows(ImageNotFoundException.class, () -> imageService.findImage(1000L));
        }

    }
}