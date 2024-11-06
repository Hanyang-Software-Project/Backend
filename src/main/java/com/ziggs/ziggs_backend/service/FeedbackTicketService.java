package com.ziggs.ziggs_backend.service;

import com.ziggs.ziggs_backend.entity.FeedbackTicket;
import com.ziggs.ziggs_backend.repository.FeedbackTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackTicketService {

    @Autowired
    private FeedbackTicketRepository feedbackTicketRepository;

    public List<FeedbackTicket> getAllFeedbackTickets() {
        return feedbackTicketRepository.findAll();
    }

    public Optional<FeedbackTicket> getFeedbackTicketById(Long id) {
        return feedbackTicketRepository.findById(id);
    }

    public FeedbackTicket saveFeedbackTicket(FeedbackTicket feedbackTicket) {
        return feedbackTicketRepository.save(feedbackTicket);
    }

    public void deleteFeedbackTicket(Long id) {
        feedbackTicketRepository.deleteById(id);
    }
}
