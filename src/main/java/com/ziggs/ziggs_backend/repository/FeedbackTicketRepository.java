package com.ziggs.ziggs_backend.repository;

import com.ziggs.ziggs_backend.entity.FeedbackTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackTicketRepository extends JpaRepository<FeedbackTicket, Long> {
}
