package kz.iitu.demo.service;

import kz.iitu.demo.entity.Publisher;

import java.util.List;

public interface PublisherService {
    public List<Publisher> findAllPublishers();

    public Publisher findPublisherById(Long id);

    public Publisher findPublisherByName(String name);

    public void addPublisher(Publisher publisher);

    public void deletePublisher(Long id);

    public void updatePublisher(Publisher publisher);
}
