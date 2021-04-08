package kz.iitu.demo.service.impl;

import kz.iitu.demo.entity.Publisher;
import kz.iitu.demo.repository.PublisherRepository;
import kz.iitu.demo.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public List<Publisher> findAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher findPublisherById(Long id) {
        return publisherRepository.findById(id).get();
    }

    @Override
    public Publisher findPublisherByName(String name) {
        return publisherRepository.findByName(name);
    }


    @Override
    public void addPublisher(Publisher publisher) {
        publisherRepository.saveAndFlush(publisher);
    }

    @Override
    public void deletePublisher(Long id) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);

        if (publisherOptional.isPresent()) {
            publisherRepository.deleteById(id);
        }
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(publisher.getId());

        if (publisherOptional.isPresent()) {
            Publisher dbPublisher = publisherOptional.get();
            dbPublisher.setName(publisher.getName());
            dbPublisher.setBooks(publisher.getBooks());
            publisherRepository.saveAndFlush(dbPublisher);
        }
    }
}
