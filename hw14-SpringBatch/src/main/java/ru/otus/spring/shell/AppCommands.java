package ru.otus.spring.shell;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AppCommands {

    private final Job migrateTables;
    private final JobLauncher jobLauncher;

    public AppCommands(JobLauncher jobLauncher, Job migrateTables) {
        this.migrateTables = migrateTables;
        this.jobLauncher = jobLauncher;
    }

    @ShellMethod(value = "migrate", key = {"mgr", "migrate"})
    public void migrate() throws Exception {
        JobExecution execution = jobLauncher.run(migrateTables, new JobParametersBuilder()
                .toJobParameters());
        System.out.println(execution);
    }
}
