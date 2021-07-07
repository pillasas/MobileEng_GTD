export interface ToDo {
    id?: number;
    projectId?: number;
    categoryId?: number;
    title?: string;
    description?: string;
    time?: Date;
    duration?: number;
    done?: boolean;
    //Enum definieren
    priority?: Enumerator;
    foto?: Uint16Array;
}