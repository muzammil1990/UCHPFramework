/**
 * Created by a232625 on 2016-07-22.
 */
import {Router} from 'express';
import * as controller from './usergroup.controller';

var router = new Router();

router.get('/', controller.index);
router.get('/:id', controller.show);
router.post('/', controller.create);
router.put('/:id', controller.update);
router.patch('/:id', controller.update);
router.delete('/:id', controller.destroy);

module.exports = router;
