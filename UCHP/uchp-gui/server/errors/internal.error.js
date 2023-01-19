function InternalError(message) {
  this.code = 500;
  this.name = 'InternalServerError';
  this.message = message || 'Internal Server Error';
}
InternalError.prototype = Object.create(Error.prototype);
InternalError.prototype.constructor = InternalError;

module.exports = InternalError;
