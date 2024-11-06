package com.ziggs.ziggs_backend.controller;

import com.ziggs.ziggs_backend.entity.FeedbackTicket;
import com.ziggs.ziggs_backend.service.FeedbackTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedbackTickets")
public class FeedbackTicketController {

    @Autowired
    private FeedbackTicketService feedbackTicketService;

    @GetMapping
    public List<FeedbackTicket> getAllFeedbackTickets() {
        return feedbackTicketService.getAllFeedbackTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackTicket> getFeedbackTicketById(@PathVariable Long id) {
        Optional<FeedbackTicket> feedbackTicket = feedbackTicketService.getFeedbackTicketById(id);
        return feedbackTicket.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FeedbackTicket> createFeedbackTicket(@RequestBody FeedbackTicket feedbackTicket) {
        FeedbackTicket savedFeedbackTicket = feedbackTicketService.saveFeedbackTicket(feedbackTicket);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFeedbackTicket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackTicket> updateFeedbackTicket(@PathVariable Long id, @RequestBody FeedbackTicket feedbackTicketDetails) {
        Optional<FeedbackTicket> updatedFeedbackTicket = feedbackTicketService.getFeedbackTicketById(id)
                .map(ticket -> {
                    // Set updated properties here
                    return feedbackTicketService.saveFeedbackTicket(feedbackTicketDetails);
                });
        return updatedFeedbackTicket.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedbackTicket(@PathVariable Long id) {
        feedbackTicketService.deleteFeedbackTicket(id);
        return ResponseEntity.noContent().build();
    }
}