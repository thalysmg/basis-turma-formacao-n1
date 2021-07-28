import { StatusTarefa } from "../enum/status-tarefa";

export class Tarefa {
    id: number;
    nome: string;
    dataInicio: Date;
    dataConclusao: Date;
    status: StatusTarefa;
    idResponsavel: number;
}