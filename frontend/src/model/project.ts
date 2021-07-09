import { ToDo } from "./todo";

export interface Project {
    id?: number;
    title?: string;
    toDos?: Array<ToDo>;
}
