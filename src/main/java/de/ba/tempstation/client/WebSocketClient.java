package de.ba.tempstation.client;

import de.ba.tempstation.db.model.MeasuringData;
import de.ba.tempstation.db.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

public class WebSocketClient {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private EntityRepository<MeasuringData> entityRepository;

    public void sendMeasuringData(int measuringDataId) {
        MeasuringData measuringData = entityRepository.getEntityById(measuringDataId, MeasuringData.class);
        messagingTemplate.convertAndSend("/topic/data/location/" + measuringData.getLocationId(), measuringData);
    }
}
