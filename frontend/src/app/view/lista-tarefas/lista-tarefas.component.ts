import { Component, OnInit } from '@angular/core';
import { Table } from 'primeng';
import { Page } from 'src/app/components/util/page';
import { Tarefa } from 'src/app/model/Tarefa';
import { TarefaService } from 'src/app/services/tarefa.service';

@Component({
  selector: 'app-lista-tarefas',
  templateUrl: './lista-tarefas.component.html',
  styleUrls: ['./lista-tarefas.component.css']
})
export class ListaTarefasComponent implements OnInit {

  dataTable: Table;

  tarefas: Page<Tarefa>;

  tarefa: Tarefa = new Tarefa();
  
  constructor(private tarefaService: TarefaService) { }

  ngOnInit(): void {
    this.listarTarefas();
  }

  public listarTarefas() {
    this.tarefaService.listar({ query: this.tarefa.nome }, this.dataTable).subscribe((response) => {
      this.tarefas = response;
    });
  }

}
