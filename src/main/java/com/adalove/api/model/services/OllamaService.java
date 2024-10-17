package com.adalove.api.model.services;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.utils.OptionsBuilder;
import io.github.ollama4j.utils.PromptBuilder;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

public class OllamaService {

    public String getOllamaResponse(String imagePath) throws OllamaBaseException, IOException, InterruptedException {
        String host = "http://localhost:11434/";
        String model = "minicpm-v:latest";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(400);


        PromptBuilder buildMsg =
                new PromptBuilder()
                        .addLine("The document is a clinical record of a patient from a psychiatric clinic.")
                        .addSeparator()
                        .addLine("Please extract the following information from the document:")
                        .addSeparator()
                        .addLine("At the top of the page, below the title \"Clinical Identification Form\":")
                        .addLine("Extract the name and CPF (which is similar to an ID and has eleven numbers) from the fields \"Name:\" and \"CPF:\".")
                        .addSeparator()
                        .addLine("Now, near the middle of the page, below the underlined text \"Psychological Anamnesis\":")
                        .addLine("Extract the patient observations from the field \"Observations:\".")
                        .addSeparator()
                        .addLine("Now return ONLY the 3 answers separated by commas, each answer must be enclosed in double quotes, as follows: \"name\", \"cpf\", \"observations\".")
                        .addLine("Follow the commands above strictly, step by step.");



        OllamaResult result = ollamaAPI.generateWithImageFiles(model,
                buildMsg.build(),
                List.of(new File(imagePath)),
                new OptionsBuilder().build()
        );
        System.out.println(result.getResponse());

        return result.getResponse();
    }
}

