package com.basis.campina.xtarefas.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "anexo")
public class Anexo {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_anexo")
    @SequenceGenerator(name = "sq_anexo", sequenceName = "sq_anexo", allocationSize = 1)
    private Long id;

    @Column(name = "path_file", nullable = false)
    private String file;

    @Column(name = "nome", nullable = false)
    private String fileName;

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "id_tarefa", nullable = false)
    private Tarefa tarefa;
}
