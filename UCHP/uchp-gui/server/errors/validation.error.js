function ValidationError(message) {
  this.code = 400;
  this.name = 'NotFoundError';
  this.message = message || 'Object is invalid';
}

ValidationError.prototype = Object.create(Error.prototype);
ValidationError.prototype.constructor = ValidationError;

module.exports = ValidationError;
