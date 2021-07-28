import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Table } from 'primeng';
import { Observable } from 'rxjs';
import { Page } from '../components/util/page';
import { RequestUtil } from '../components/util/request-util';
import { Tarefa } from '../model/Tarefa';

@Injectable()
export class TarefaService {

  constructor(private httpClient: HttpClient) { }

  listar(query: any, datatable: Table): Observable<Page<Tarefa>> {
    const options = { params: RequestUtil.getRequestParamsTable(datatable)};
    return this.httpClient.post<Page<Tarefa>>('http://localhost:4200/api/tarefas/pesquisar', query, options);
  }
}
