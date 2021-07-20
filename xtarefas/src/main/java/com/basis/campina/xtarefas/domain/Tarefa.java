package com.basis.campina.xtarefas.domain;

import com.basis.campina.xtarefas.enumeration.StatusTarefa;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "tarefa")
public class Tarefa {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tarefa")
    @SequenceGenerator(name = "sq_tarefa", sequenceName = "sq_tarefa", allocationSize = 1)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "dt_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "dt_conclusao", nullable = false)
    private LocalDate dataConclusao;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private StatusTarefa status;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_responsavel", referencedColumnName = "id", nullable = false)
    private Responsavel responsavel;
}
