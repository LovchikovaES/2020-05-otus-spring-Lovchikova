package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.io.IOService;

@Service
public class IOMessageService {

    private final IOService ioService;
    private final LocalizedMessageService localizedMessageService;

    public IOMessageService(IOService ioService,
                            LocalizedMessageService localizedMessageService) {
        this.ioService = ioService;
        this.localizedMessageService = localizedMessageService;
    }

    public void output(Object object) {
        ioService.output(object);
    }

    public void outputMessage(String bundleProperty) {
        ioService.output(localizedMessageService.getMessage(bundleProperty));
    }

    public String get() {
        return ioService.get();
    }
}
