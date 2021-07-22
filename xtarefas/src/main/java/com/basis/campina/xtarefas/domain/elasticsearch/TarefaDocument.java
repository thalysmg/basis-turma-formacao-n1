package com.basis.campina.xtarefas.domain.elasticsearch;

import com.basis.campina.xtarefas.enumeration.StatusTarefa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Document(indexName = "index-tarefa")
@NoArgsConstructor
public class TarefaDocument extends BaseDocument {

    @MultiField(mainField = @Field(type = FieldType.Text, store = true,
            analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true,
                    analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String nome;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true,
            analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true,
                    analyzer = TRIM_CASE_INSENSITIVE,
                    format = DateFormat.custom, pattern = DATE_PATTERN)})
    private String dataInicio;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true,
            analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true,
                    analyzer = TRIM_CASE_INSENSITIVE,
                    format = DateFormat.custom, pattern = DATE_PATTERN)})
    private String dataConclusao;

    @MultiField(mainField = @Field(type = FieldType.Integer, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Integer, store = true)})
    private int status;

    @MultiField(mainField = @Field(type = FieldType.Long, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Long, store = true)})
    private  Long idResponsavel;

    public TarefaDocument(Long id, String nome, LocalDate dataInicio, LocalDate dataConclusao, StatusTarefa status, Long idResponsavel) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio != null ? dataInicio.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) : null;
        this.dataConclusao = dataConclusao != null ? dataConclusao.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) : null;
        this.status = status.ordinal();
        this.idResponsavel = idResponsavel;
    }
}
