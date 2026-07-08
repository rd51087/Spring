import { Routes } from '@angular/router';

import { AddPersonComponent } from './components/add-person/add-person.component';
import { DetailsComponent } from './components/details/details.component';
import { ListComponent } from './components/list/list.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

export const routes: Routes = [
	{
		path: '',
		component: ListComponent
	},
	{
		path: 'details/:id',
		component: DetailsComponent
	},
	{
		path: 'add',
		component: AddPersonComponent
	},
	{
		path: '**',
		component: NotFoundComponent
	}
];
