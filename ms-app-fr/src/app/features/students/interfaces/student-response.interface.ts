import { IStudent } from './student.interface';

export interface IStudentResponse {
  message: string;
  status: string;
  data: IStudent | IStudent[];
}
