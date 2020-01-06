import {Item} from './item';
import {Mark} from './mark';

export class ShoppingList {
    id: number;
    name: string;
    description: string;
    items: Item[];
    marks: Mark[];
}
