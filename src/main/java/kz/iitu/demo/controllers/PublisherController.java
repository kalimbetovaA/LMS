package kz.iitu.demo.controllers;


import kz.iitu.demo.entity.Publisher;
import kz.iitu.demo.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("")
    public List<Publisher> findAllPublishers() {
        return publisherService.findAllPublishers();
    }

    @GetMapping("/{id}")
    public Publisher findPublisherById(@PathVariable Long id) {
        return publisherService.findPublisherById(id);
    }


    @GetMapping("/find/")
    public Publisher findPublisherByName(@RequestParam String name) {
        return publisherService.findPublisherByName(name);
    }

    @PostMapping("")
    public void addPublisher(@RequestBody Publisher publisher) {
        publisherService.addPublisher(publisher);
    }


    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
    }

    @PutMapping("/{id}")
    public void updatePublisher(@PathVariable Long id,@RequestBody Publisher publisher) {
        publisher.setId(id);
        publisherService.updatePublisher(publisher);
    }

}
