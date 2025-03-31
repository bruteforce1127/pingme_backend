package com.kucp1127.ReMindr.ReminderManagementModule.Repository;

import com.kucp1127.ReMindr.ReminderManagementModule.Model.Reminders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemindersRepository extends JpaRepository<Reminders, Long> {
    // Additional query methods can be defined here if needed
}
