import axios from 'axios';
import { API_ROOT } from "@/config/development";
import { Project } from '@/model/project';

export async function getAllProjects(): Promise<Project[]> {
    const config = {
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/project', config);
        return response.data;
    } catch (error) {
        return error;
    }
   
}

export async function getProject(projectId: number): Promise<Project> {
    const config = {
        withCredentials: true
    }
    try {
        const response = await axios.get(API_ROOT + '/api/project/' + projectId, config);
        return response.data;
    } catch (error) {
        return error;
    }
}
